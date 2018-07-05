package minhamesarpg

import org.grails.web.json.JSONObject

class ChatMesaController {

    static responseFormats = ['json']
    static allowedMethods = [
            list: "GET",
            save: "POST"
    ]
    def list (){
        respond Players.list()
    }
}
