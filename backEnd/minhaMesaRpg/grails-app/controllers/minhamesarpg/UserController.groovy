package minhamesarpg
import grails.converters.JSON
import org.grails.web.json.JSONObject
class UserController {
    static responseFormats = ['json']
    static allowedMethods = [
            list: "GET",
            save: "POST"
    ]
    def userService

    def list() {
        respond User.list()
    }
    def save () {
        User user = new User()
        JSONObject json = request.JSON
        println '------------' * 20
        userService.serviceConvertJson(json, user)

        respond json
    }
}
