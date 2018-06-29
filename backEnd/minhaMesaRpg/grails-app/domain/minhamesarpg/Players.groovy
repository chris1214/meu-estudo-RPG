package minhamesarpg
import grails.rest.Resource
@Resource(uri = '/application/json')
class Players {
    String user
    Integer userId
    Integer mesaId
    Integer id
    static constraints = {
    }
}
