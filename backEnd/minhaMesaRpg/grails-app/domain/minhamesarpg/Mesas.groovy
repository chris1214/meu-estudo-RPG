package minhamesarpg

class Mesas {
    String tipoDoSistema
    String valueTiposDeMesas
    String faixaEtaria
    String title
    String mestre
    String playersMin
    String playersMax
    String mapaPrincipal
    String descricao
    String type
    String url
    String size
    static constraints = {
        tipoDoSistema nullable: false,blanck: false
        url nullable: false,blanck: false
        valueTiposDeMesas nullable: false,blanck: false
        faixaEtaria nullable: false,blanck: false
        title nullable: false,blanck: false
        mestre nullable: false,blanck: false
        mapaPrincipal nullable: false,blanck: false
        descricao nullable: false,blanck: false
    }
}
