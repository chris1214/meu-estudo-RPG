package minhamesarpg
import grails.rest.Resource
@Resource(uri = '/application/json')
class Players {
    String user
    int mesa
    static constraints = {
        user nullable: false,blanck: false
        mesa nullable: false,blanck: false
    }
}
