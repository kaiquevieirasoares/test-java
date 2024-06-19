package br.com;

import br.com.models.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Funcionario> funcionarios = new ArrayList<>();
        List<Funcionario> funcionariosGerados = gerarFuncionarios();
        //3.1
        System.out.println();
        for(Funcionario funcionario : funcionariosGerados){
            funcionarios.add(funcionario);
            System.out.println(funcionario);
        }
        //3.2
        System.out.println();
        funcionarios.removeIf(funcionario -> Objects.equals(funcionario.getNome(), "João"));

        //3.4

        System.out.println();
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.valueOf(1.10));
            funcionario.setSalario(novoSalario);
        }

        //3.5
        System.out.println();
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            funcionariosPorFuncao.computeIfAbsent(funcao, k -> new ArrayList<>()).add(funcionario);
        }

        //3.6 - Exibir os funcionários agrupados por função
        System.out.println();
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("  - " + funcionario.getNome());
            }
        }


        // 3.8 - Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(dateFormatter));
                System.out.println();
            }

    }
        //3.9
        System.out.println();
        int maiorIdade = 0;
        Funcionario funcionarioMaisVelho = null;

        for (Funcionario funcionario : funcionarios) {
            int idade = calcularIdade(funcionario.getDataNascimento());
            if (idade > maiorIdade) {
                maiorIdade = idade;
                funcionarioMaisVelho = funcionario;
            }
        }

        if (funcionarioMaisVelho != null) {
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + maiorIdade);
        } else {
            System.out.println("Não há informações suficientes para determinar o funcionário mais velho.");
        }


        //3.10
        System.out.println();
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));

        System.out.println("Lista de funcionários por ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        //3.11
        System.out.println();
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        System.out.println("Total dos salários dos funcionários: " + totalSalarios);

        //3.12
        System.out.println();
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }

}


    public static List<Funcionario>gerarFuncionarios(){
        List<Funcionario> funcionario = new ArrayList<Funcionario>();
        funcionario.add(new Funcionario("Maria", "18-10-2000", new BigDecimal(2009.44), "Operador"));
        funcionario.add(new Funcionario("João","12-05-1990", new BigDecimal(2284.38), "Operador"));
        funcionario.add(new Funcionario("Caio", "02-05-1961", new BigDecimal(9836.14), "Cordenador"));
        funcionario.add(new Funcionario("Miguel","14-10-1998", new BigDecimal(19119.88), "Diretor"));
        funcionario.add(new Funcionario("ALice", "05-01-1995", new BigDecimal(2234.68), "Recepcionista"));
        funcionario.add(new Funcionario("heitor","19-11-1999", new BigDecimal(1582.72), "Operador"));
        funcionario.add(new Funcionario("Arthur", "31-03-1993", new BigDecimal(4071.84), "Contador"));
        funcionario.add(new Funcionario("LAura","08-07-1994", new BigDecimal(3017.45), "Gerente"));
        funcionario.add(new Funcionario("Heloisa", "24-09-1996", new BigDecimal(1606.85), "Eletricista"));
        funcionario.add(new Funcionario("Helena","02-09-1996", new BigDecimal(2799.93), "Gerente"));

    return funcionario;
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return hoje.getYear() - dataNascimento.getYear();
    }
}