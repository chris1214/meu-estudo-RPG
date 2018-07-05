package minhamesarpg
import grails.rest.Resource
@Resource(uri = '/application/json')
class Mesas {
    String tipoDoSistema
    String url
    String valueTiposDeMesas
    String faixaEtaria
    String title
    String mestre
    String mapaPrincipal
    String descricao
    Integer playersMin
    Integer playersMax
    String type
    Integer size
    Integer vagas

    static constraints = {
        tipoDoSistema nullable: false,blanck: false
        url nullable: false,blanck: false
        valueTiposDeMesas nullable: false,blanck: false
        faixaEtaria nullable: false,blanck: false
        title nullable: false,blanck: false
        mestre nullable: false,blanck: false
        mapaPrincipal nullable: false,blanck: false
        descricao nullable: false,blanck: false
        playersMin nullable: false,blanck: false
        playersMax nullable: false,blanck: false

        type nullable: true
        size nullable: true
        vagas nullable: true
    }
}
