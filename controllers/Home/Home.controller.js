const Subject = require('../../models/Subject.model')
const User = require('../../models/User.model')
const randomPreferences = require('../../utils/randomPreferences.utils')

const HomeController = {
  landingHome: async (req, res, next) => {
    try {
      let preferences = []

      if (req.params?.id) {
        const user = await User.findById(req.params?.id)
        console.log({ user })
        preferences =
          user?.preferences.length === 0
            ? await randomPreferences()
            : user.preferences
      } else preferences = await randomPreferences()

      const preferredSubject = await Subject.find({
        categoryId: { $in: preferences }
      }).limit(5)

      const filteredSubjects = preferredSubject.map(
        ({ _id: id, name, courses, url }) => ({
          id,
          name,
          url,
          courseCount: courses.length
        })
      )

      const preferredSubjectsId = preferredSubject.map(({ _id }) => _id)

      const tutors = await User.find({
        subjectsId: { $in: preferredSubjectsId }
      })
        .populate('subjectsId', { name: 1 })
        .limit(5)

      const mappedTutors = tutors.map(
        ({ fullName, imgUrl, subjectsId, puntuation, url }) => ({
          fullName,
          imgUrl,
          puntuation,
          url,
          subjects: subjectsId.map(({ name }) => name)
        })
      )

      return res.status(200).json({
        error: false,
        subjects: filteredSubjects,
        tutors: mappedTutors
      })
    } catch (error) {
      console.log({ error })
      next(error)
    }
  }
}

module.exports = HomeController
