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
    println '**************'*50
    println params
    println params.email
    println params.password
    println '**************'*50
    def usuarios = Usuarios.createCriteria()
    def resultado = usuarios.list {
        like("email", params.email)
        like("password", params.password)
    }
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
