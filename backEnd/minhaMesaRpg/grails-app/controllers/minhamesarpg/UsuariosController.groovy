package minhamesarpg

import org.grails.web.json.JSONObject

class UsuariosController {

static responseFormats = ['json']
static allowedMethods = [
        list: "GET",
        save: "POST"
]
def usuariosService

def getUsuario() {
    def usuarios = Usuarios.createCriteria()
    def resultado = usuarios.list {
        like("email", params.email)
        like("password", params.password)
    }
    println resultado
    respond resultado
}

def list() {
    respond Usuarios.list()
}

def save () {
    Usuarios usuarios = new Usuarios()
    JSONObject json = request.JSON
    usuariosService.serviceConvertJson(json, usuarios)

    respond json
}

}
