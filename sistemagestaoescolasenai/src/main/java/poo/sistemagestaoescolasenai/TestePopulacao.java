package poo.sistemagestaoescolasenai;

import java.util.Date;
import java.util.Map;

public class TestePopulacao {

    // Popula usuários
    public static void populaUsuarios(Map<String, String[]> usuarios) {
        usuarios.put("admin", new String[] { "123", "admin" });
        usuarios.put("aluno1", new String[] { "123", "aluno" });
        usuarios.put("professor1", new String[] { "123", "professor" });
    }

    public static void populaAlunos() {
        // Criar alguns endereços
        Endereco endereco1 = new Endereco("Rua A", "123", "", "Centro", UnidadeFederal.SP, "01001-000");
        Endereco endereco2 = new Endereco("Rua B", "456", "Apto 101", "Jardins", UnidadeFederal.RJ, "22011-001");
        Endereco endereco3 = new Endereco("Rua C", "789", "", "Barra", UnidadeFederal.BA, "40140-130");
        Endereco endereco4 = new Endereco("Rua D", "101", "Casa 2", "Alphaville", UnidadeFederal.SP, "06542-001");
        Endereco endereco5 = new Endereco("Rua E", "202", "Bloco B", "Copacabana", UnidadeFederal.RJ, "22060-030");
        Endereco endereco6 = new Endereco("Rua F", "303", "", "Pituba", UnidadeFederal.BA, "41810-000");

        // Criar alguns responsáveis
        Pessoa responsavel1 = new Pessoa("Maria Silva", "111.111.111-11", "01/01/1970", endereco1, "(11) 99999-9999",
                "maria@email.com");
        Pessoa responsavel2 = new Pessoa("João Oliveira", "222.222.222-22", "02/02/1975", endereco2, "(21) 88888-8888",
                "joao@email.com");
        Pessoa responsavel3 = new Pessoa("Ana Santos", "333.333.333-33", "03/03/1980", endereco3, "(71) 77777-7777",
                "ana@email.com");
        Pessoa responsavel4 = new Pessoa("Carlos Pereira", "444.444.444-44", "04/04/1965", endereco4, "(11) 91234-5678",
                "carlos.pereira@email.com");
        Pessoa responsavel5 = new Pessoa("Fernanda Costa", "555.555.555-55", "05/05/1970", endereco5, "(21) 92345-6789",
                "fernanda.costa@email.com");
        Pessoa responsavel6 = new Pessoa("Roberto Lima", "666.666.666-66", "06/06/1980", endereco6, "(71) 93456-7890",
                "roberto.lima@email.com");

        // Cria professores
        Professor professor1 = new Professor("Ana Santos", "111.222.333-44", "10/10/1980", endereco1, "(11) 91234-5678",
                "ana.santos@exemplo.com", "Nenhum", 0, 0);
        Professor.adicionarProfessor(professor1);
        Professor professor2 = new Professor("Carlos Oliveira", "222.333.444-55", "20/11/1975", endereco2,
                "(21) 92345-6789", "carlos.oliveira@exemplo.com", "Nenhum", 0, 0);
        Professor.adicionarProfessor(professor2);
        Professor professor3 = new Professor("Fernanda Lima", "333.444.555-66", "15/12/1985", endereco3,
                "(31) 93456-7890", "fernanda.lima@exemplo.com", "Nenhum", 0, 0);
        Professor.adicionarProfessor(professor3);
        Professor professor4 = new Professor("José Almeida", "444.555.666-77", "25/01/1970", endereco4,
                "(41) 94567-8901", "jose.almeida@exemplo.com", "Nenhum", 0, 0);
        Professor.adicionarProfessor(professor4);
        Professor professor5 = new Professor("Mariana Souza", "555.666.777-88", "05/02/1990", endereco5,
                "(51) 95678-9012", "mariana.souza@exemplo.com", "Nenhum", 0, 0);
        Professor.adicionarProfessor(professor5);

        // Criar algumas disciplinas
        Disciplina matematica = new Disciplina(1, "Matemática", 60, professor1, Turno.MANHA);
        professor1.addDisciplina(matematica);
        Disciplina portugues = new Disciplina(2, "Português", 60, professor2, Turno.MANHA);
        professor2.addDisciplina(portugues);
        Disciplina historia = new Disciplina(3, "História", 45, professor3, Turno.TARDE);
        professor3.addDisciplina(historia);
        Disciplina quimica = new Disciplina(4, "Química", 50, professor4, Turno.TARDE);
        professor4.addDisciplina(quimica);
        Disciplina fisica = new Disciplina(5, "Física", 60, professor4, Turno.NOITE);
        professor4.addDisciplina(fisica);
        Disciplina biologia = new Disciplina(6, "Biologia", 45, professor5, Turno.MANHA);
        professor5.addDisciplina(biologia);

       

        // Criar e adicionar alunos
        Aluno aluno1 = new Aluno("Carlos Silva", "444.444.444-44", "10/05/2000", endereco1, "(11) 98765-4321",
                "carlos@email.com", responsavel1, "Aluno exemplar", 0, 0, "", 1);
        aluno1.addDisciplina(portugues);
        portugues.addAluno(aluno1);
        aluno1.addDisciplina(historia);
        historia.addAluno(aluno1);
        aluno1.addDisciplina(quimica);
        quimica.addAluno(aluno1);
        aluno1.addDisciplina(fisica);
        fisica.addAluno(aluno1);
        aluno1.addDisciplina(matematica);
        matematica.addAluno(aluno1);
        aluno1.addDisciplina(biologia);
        biologia.addAluno(aluno1);
        aluno1.addNota(new Notas(matematica, 8.5, new Date(), aluno1));
        aluno1.addNota(new Notas(portugues, 7.0, new Date(), aluno1));
        Aluno.adicionarAluno(aluno1);

        Aluno aluno2 = new Aluno("Mariana Oliveira", "555.555.555-55", "15/08/2001", endereco2, "(21) 98765-1234",
                "mariana@email.com", responsavel2, "Participa do grupo de teatro", 2, 10, "", 1);
        aluno2.addDisciplina(portugues);
        portugues.addAluno(aluno2);
        aluno2.addDisciplina(historia);
        historia.addAluno(aluno2);
        aluno2.addDisciplina(quimica);
        quimica.addAluno(aluno2);
        aluno2.addDisciplina(fisica);
        fisica.addAluno(aluno2);
        aluno2.addDisciplina(matematica);
        matematica.addAluno(aluno2);
        aluno2.addDisciplina(biologia);
        biologia.addAluno(aluno2);
        //aluno2.addNota(new Notas(portugues, 9.0, new Date(), aluno2));
        //aluno2.addNota(new Notas(historia, 8.0, new Date(), aluno2));
        Aluno.adicionarAluno(aluno2);

        Aluno aluno3 = new Aluno("Pedro Santos", "666.666.666-66", "20/03/2002", endereco3, "(71) 98765-5678",
                "pedro@email.com", responsavel3, "Atleta destaque", 1, 15, "Atestado médico para Ed. Física", 2);
        aluno3.addDisciplina(portugues);
        portugues.addAluno(aluno3);
        aluno3.addDisciplina(historia);
        historia.addAluno(aluno3);
        aluno3.addDisciplina(quimica);
        quimica.addAluno(aluno3);
        aluno3.addDisciplina(fisica);
        fisica.addAluno(aluno3);
        aluno3.addDisciplina(matematica);
        matematica.addAluno(aluno3);
        aluno3.addDisciplina(biologia);
        biologia.addAluno(aluno3);
        aluno3.addNota(new Notas(matematica, 7.5, new Date(), aluno3));
        aluno3.addNota(new Notas(historia, 8.5, new Date(), aluno3));
        Aluno.adicionarAluno(aluno3);

        Aluno aluno4 = new Aluno("Julia Pereira", "777.777.777-77", "25/11/2003", endereco4, "(11) 98765-6789",
                "julia.pereira@email.com", responsavel4, "Envolvida em projetos sociais", 1, 5, "", 1);
        aluno4.addDisciplina(portugues);
        portugues.addAluno(aluno4);
        aluno4.addDisciplina(historia);
        historia.addAluno(aluno4);
        aluno4.addDisciplina(quimica);
        quimica.addAluno(aluno4);
        aluno4.addDisciplina(fisica);
        fisica.addAluno(aluno4);
        aluno4.addDisciplina(matematica);
        matematica.addAluno(aluno4);
        aluno4.addDisciplina(biologia);
        biologia.addAluno(aluno4);
        aluno4.addNota(new Notas(quimica, 8.0, new Date(), aluno4));
        aluno4.addNota(new Notas(fisica, 9.0, new Date(), aluno4));
        Aluno.adicionarAluno(aluno4);

        Aluno aluno5 = new Aluno("Ricardo Costa", "888.888.888-88", "10/09/2004", endereco5, "(21) 99876-5432",
                "ricardo.costa@email.com", responsavel5, "Participa da equipe de robótica", 0, 8, "", 1);
                aluno2.addDisciplina(portugues);
        aluno5.addDisciplina(historia);
        historia.addAluno(aluno5);
        aluno5.addDisciplina(quimica);
        quimica.addAluno(aluno5);
        aluno5.addDisciplina(fisica);
        fisica.addAluno(aluno5);
        aluno5.addDisciplina(matematica);
        matematica.addAluno(aluno5);
        aluno5.addDisciplina(biologia);
        biologia.addAluno(aluno5);
        aluno5.addNota(new Notas(fisica, 7.5, new Date(), aluno5));
        aluno5.addNota(new Notas(biologia, 8.5, new Date(), aluno5));
        Aluno.adicionarAluno(aluno5);

        Aluno aluno6 = new Aluno("Ana Lima", "999.999.999-99", "05/07/2005", endereco6, "(71) 97654-3210",
                "ana.lima@email.com", responsavel6, "Representante de turma", 3, 7, "", 2);
        aluno6.addDisciplina(portugues);
        portugues.addAluno(aluno6);
        aluno6.addDisciplina(historia);
        historia.addAluno(aluno6);
        aluno6.addDisciplina(quimica);
        quimica.addAluno(aluno6);
        aluno6.addDisciplina(fisica);
        fisica.addAluno(aluno6);
        aluno6.addDisciplina(matematica);
        matematica.addAluno(aluno6);
        aluno6.addDisciplina(biologia);
        biologia.addAluno(aluno6);
        aluno6.addNota(new Notas(biologia, 9.5, new Date(), aluno6));
        aluno6.addNota(new Notas(quimica, 7.0, new Date(), aluno6));
        Aluno.adicionarAluno(aluno6);



    }

}
