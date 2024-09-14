package poo.sistemagestaoescolasenai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class SistemagestaoescolasenaiApplication {

    // Armazenar os usuários e suas senhas
    private static final Map<String, String[]> usuarios = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(SistemagestaoescolasenaiApplication.class, args);

        // Inicializar dados teste
        TestePopulacao.populaAlunos();
        TestePopulacao.populaUsuarios(usuarios);// Popula os usuários e senhas

        final int maxTentativas = 3;//define a quantidade de tentativas

        Scanner scanner = new Scanner(System.in);

        int tentativas = 0;
        String perfil = null;

        while (tentativas < maxTentativas) {
            System.out.println("\n=============================================");
            System.out.println("|                  * SGE *                  |");
            System.out.println("|       - Sistema de Gestão Escolar -       |");
            System.out.println("---------------------------------------------");
            System.out.print("    Login: ");
            String login = scanner.nextLine();
            System.out.print("    Senha: ");
            String senha = scanner.nextLine();

            // Verifica as credenciais
            if (verificarCredenciais(login, senha)) {
                perfil = usuarios.get(login)[1];
                System.out.println("\n---------------------------------------------");
                System.out.println("|              Acesso Permitido             |");
                System.out.println("=============================================");
                break;
            } else {
                tentativas++;
                if (tentativas == maxTentativas) {
                    System.out.println("\n---------------------------------------------");
                    System.out.println("|          Seu acesso foi bloqueado         |");
                    System.out.println("=============================================");
                } else {
                    if (tentativas == maxTentativas - 1) {
                        System.out.println("\n---------------------------------------------");
                        System.out.println("|              Última tentativa!            |");
                        System.out.println("=============================================");
                    } else {
                        System.out.println("\n---------------------------------------------");
                        System.out.println("|              Dados inválidos              |");
                        System.out.println("|             Tente novamente!              |");
                        System.out.println("=============================================");
                    }
                }
            }
        }

        if (perfil != null) {
            mostrarMenu(perfil);
        }

        scanner.close();
    }

    private static boolean verificarCredenciais(String login, String senha) {
        return usuarios.containsKey(login) && usuarios.get(login)[0].equals(senha);
    }

    private static void mostrarMenu(String perfil) {
        switch (perfil) {
            case "admin":
                MenuAdmin.exibirMenu();
                break;
            case "aluno":
                MenuAluno.exibirMenu();
                break;
            case "professor":
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a matrícula do professor: ");
            String matriculaProfessor = scanner.nextLine();
            Professor professor = Professor.encontrarProfessorPorMatricula(matriculaProfessor);
            if (professor != null) {
                MenuProfessor menuProfessor = new MenuProfessor(professor);
                menuProfessor.exibirMenu();
            } else {
                System.out.println("Professor não encontrado.");
            }
            break;
          default:
            System.out.println("Perfil desconhecido.");
            break;

        }
    }
}

