package br.com.models;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, String dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario.setScale(1, BigDecimal.ROUND_HALF_UP);
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "Funcionario{" +
                "Nome:" + super.getNome() +
                ", DataNascimento:" + super.getDataNascimento().format(formatter) +
                ", salario=" + salario +
                ", funcao='" + funcao + '\'' +
                '}';
    }


    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
