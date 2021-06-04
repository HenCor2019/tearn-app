const express = require('express')
const {
  loginGoogle,
  allUser,
  updateToTutor,
  updateNormalUser,
  allTutor,
  oneTutor,
  deleteUser,
  deleteAll,
  oneUser,
  tutorsUser
} = require('../../controllers/User/User.controller')
const router = express.Router()

router.post('/login', loginGoogle)

router.get('/', allUser)
router.get('/:id', oneUser)
router.get('/tutor', allTutor)
router.get('/tutor/:id', oneTutor)
router.get('/tutors/:id', tutorsUser)

router.put('/update-tutor/', updateToTutor)
router.put('/', updateNormalUser)

router.delete('/:id', deleteUser)
router.delete('/', deleteAll)

module.exports = router
