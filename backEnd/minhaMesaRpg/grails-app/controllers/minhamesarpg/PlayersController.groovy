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
        int i = Integer.parseInt(params.id)
        def player = Players.createCriteria()
        def resultado = player.list {
            like("mesa", i)
        }
        respond resultado
    }
    def save(){
        JSONObject json = request.JSON
        Players players = new Players()
        int resultado = Integer.parseInt(json.mesaId)
        println players.mesa

        players.save(flush: true)
    }

}
