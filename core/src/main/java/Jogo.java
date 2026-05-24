public class Jogo
{
    private int idJogo;
    private String nomeJogo;
    private String categoria;
    private String dificuldade;

    public Jogo(int idJogo,String nomeJogo, String categoria, String dificuldade)
    {
        this.idJogo = idJogo;
        this.nomeJogo = nomeJogo;
        this.categoria = categoria;
        this.dificuldade = dificuldade;
    }
    public int getIdJogo()
    {
        return idJogo;
    }
    public String getNomeJogo()
    {
        return nomeJogo;
    }
    public String getCategoria()
    {
        return categoria;
    }
    public String getDificuldade()
    {
        return dificuldade;
    }

}
