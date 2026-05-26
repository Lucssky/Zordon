public class Usuario
{
    private int idUsuario;
    private String nome;
    private int pontuacaoTotal;

    public Usuario(int idUsuario,String nome)
    {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.pontuacaoTotal = 0;
    }

    public int getIdUsuario()
    {
        return idUsuario;
    }
    public String getNome()
    {
        return nome;
    }
    public int getPontuacaoTotal()
    {
        return pontuacaoTotal;
    }
    public void adicionarPontuacao(int pontos)
    {
        pontuacaoTotal += pontos;
    }
}
