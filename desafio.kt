

enum class Nivel {
    BASICO, INTERMEDIARIO, AVANCADO
}

data class Usuario(val name:String, val age:Int) {
    constructor() : this("",0)
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel)

class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(vararg usuario: Usuario) {
        for(u in usuario)
            inscritos.add(u)
    }

    infix fun inscrever (usuario: Usuario) {
        matricular(usuario)
    }

    override fun toString(): String {
        var str : String = "Curso de $nome\n"
        str += "\tLista de conteúdos:\n"
        var horasCurso = 0
        for(c in conteudos) {
            str += "\t\tNome:\"${c.nome}\", Carga horária: ${c.duracao} horas, Nível ${c.nivel.toString()}.\n"
            horasCurso += c.duracao
        }
        str += "\tDuração total do curso: " + horasCurso + " horas\n"
        str += "\tAnulos inscritos:\n"
        for(a in inscritos) {
            str += "\t\tNome: ${a.name}, ${a.age} anos.\n"
        }
        return str
    }
}

fun main() {
    val lucas = Usuario("Lucas",32)
    val ana = Usuario("Ana", 25)
    val pedro = Usuario("Pedro", 27)
    val maria = Usuario("Maria", 29)

    val cursoKotlinBasico =
        ConteudoEducacional("Curso de Kotlin para iniciantes", 30, Nivel.BASICO)
    val cursoKotlinIntermediario =
        ConteudoEducacional("Curso de Kotlin intermediário", 40, Nivel.INTERMEDIARIO)
    val cursoKotlinAvancado =
        ConteudoEducacional("Curso de Kotlin avançado", 50, Nivel.AVANCADO)
    val cursoScrum =
        ConteudoEducacional("Curso de Scrum", 20, Nivel.BASICO)

    val formacaoKotlin = Formacao("Formação em Kotlin", listOf(
        cursoKotlinBasico,
        cursoKotlinIntermediario,
        cursoKotlinAvancado,
        cursoScrum
    ))

    formacaoKotlin inscrever lucas
    formacaoKotlin inscrever ana
    formacaoKotlin inscrever pedro
    formacaoKotlin inscrever maria

    println(formacaoKotlin.toString())
}
