package minhamesarpg

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlayersController {

    PlayersService playersService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

}
