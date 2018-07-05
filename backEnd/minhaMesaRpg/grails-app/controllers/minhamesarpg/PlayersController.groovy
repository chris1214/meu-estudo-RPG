package minhamesarpg

import org.grails.web.json.JSONObject

class PlayersController {

    PlayersService playersService

    static responseFormats = ['json']
    static allowedMethods = [
            list: "GET",
            save: "POST"
    ]
    def list (){
        respond Players.list()
    }
    def save(){
        JSONObject json = request.JSON
        Players players = new Players(json)
        println players
        players.save(flush: true)
    }

}
