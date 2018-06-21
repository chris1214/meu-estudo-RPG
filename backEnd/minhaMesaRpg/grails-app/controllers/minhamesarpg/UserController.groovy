package minhamesarpg
import grails.converters.JSON
import org.grails.web.json.JSONObject
class UserController {
    static responseFormats = ['json']
    static allowedMethods = [
            list: "GET",
            save: "POST"
    ]

    def list() {
        respond User.list()
    }
    def save () {
        User user = new User()
        JSONObject json = request.JSON
        println '------------' * 20
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

        /*user.name = 'chris'
        user.email = 'admin@hotmail.com'
        user.password = '123456'
        user.dataNascimento = new Date()
        user.nivelDeMestragem = 1
        user.experienciaDescricao = 'alta'
        user.nivelDePlayer = 1
        user.desejo = 'mestre'
        user.experiencia = 1
        user.user = 'mestre'
        user.nivel = 'mestre'*/

        println user.name
        println user.email
        println user.password
        println user.dataNascimento
        println user.experienciaDescricao
        println user.nivelDePlayer
        println user.desejo
        println user.experiencia
        println user.user
        println user.nivel
        println '*********************' *50
        if (!user.save()) {
            user.errors.allErrors.each {
                println it
            }
        }
        println '*********************' *50
        user.save(flush: true)
        respond json
    }
}
