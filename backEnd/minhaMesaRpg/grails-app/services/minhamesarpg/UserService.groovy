package minhamesarpg

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def serviceConvertJson(json, user) {
        String date =  json.dataNascimento

        def strDate = new Date().parse("yyyy-MM-dd",date)

        user.name = json.name
        user.email = json.email
        user.password = json.password
        user.dataNascimento = strDate
        user.nivelDeMestragem = json.nivelDeMestragem
        user.experienciaDescricao = json.experienciaDescricao
        user.nivelDePlayer = json.nivelDePlayer
        user.desejo = json.tags.desejo
        user.experiencia = json.tags.experiencia
        user.user = json.tags.user
        user.nivel = json.tags.nivel

        user.save(flush: true)
    }
}