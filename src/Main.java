import dao.AlunoDAO;
import model.Aluno;
import util.ConsoleUtils;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        exibirMenu();
    }

    /**
     * Metodo para exibir o menu principal da biblioteca.
     */
    public static void exibirMenu() throws SQLException {
        int opcao;
        do {
            System.out.println("\n===== BIBLIOTECA APRENDER MAIS =====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Registrar Empréstimo");
            System.out.println("4. Registrar Devolução");
            System.out.println("5. Gerar Relatórios");
            System.out.println("0. Sair");

            opcao = ConsoleUtils.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    System.out.println("Opção: Cadastrar Livro");
                    break;
                case 3:
                    System.out.println("Opção: Registrar Empréstimo");
                    break;
                case 4:
                    System.out.println("Opção: Registrar Devolução");
                    break;
                case 5:
                    System.out.println("Opção: Gerar Relatórios");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void cadastrarAluno() throws SQLException {
        System.out.println("=== Cadastro de Aluno ===");
        String nome = ConsoleUtils.readString("Nome do Aluno: ");
        String matricula = ConsoleUtils.readString("Matrícula: ");
        LocalDate dataNascimento = ConsoleUtils.readDate("Data de nascimento");

        Aluno aluno = new Aluno();
        aluno.setNomeAluno(nome);
        aluno.setMatricula(matricula);
        aluno.setDataNascimento(dataNascimento);

        AlunoDAO alunoDAO = new AlunoDAO();
        boolean sucesso = alunoDAO.insert(aluno);

        if (sucesso) {
            System.out.println("Aluno cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar aluno. Por favor, tente novamente!");
        }
    }
}
