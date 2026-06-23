package entities;

public class Cliente {

    private String nome;
    private String email;

    public Cliente(String name, String email) {
        this.nome = name;
        this.email = email;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
