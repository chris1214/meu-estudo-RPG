package minhamesarpg
import grails.rest.Resource
@Resource(uri = '/application/json')

class Usuarios {
    String name
    String email
    String password
    Date dataNascimento
    Integer nivelDeMestragem
    Integer nivelDePlayer
    String experienciaDescricao
    String desejo
    String experiencia
    String user
    String nivel

    static constraints = {
        name nullable: false,blanck: false
        desejo nullable: false,blanck: false
        experiencia nullable: false,blanck: false
        email nullable: false,black: false, email: true, unique: true
        password size: 6..35
        nivelDeMestragem min: 0
        nivelDePlayer min: 0

    }
}
