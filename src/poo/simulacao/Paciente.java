package poo.simulacao;
/** Modelo fictício e discreto; os números são parâmetros pedagógicos, sem uso clínico. */
public final class Paciente {
    private double temperatura=37;
    private int pac=100000;
    private int minutosQuente=0,minutosPacBaixo=0;
    private boolean vivo=true;
    public double temperatura(){return temperatura;}
    public int pac(){return pac;}
    public boolean vivo(){return vivo;}
    public void surto(double aumento,int reducao){
        if(!Double.isFinite(aumento)||aumento<0||reducao<0)throw new IllegalArgumentException("Surto inválido");
        if(vivo){temperatura+=aumento;pac=Math.max(0,pac-reducao);}
    }
    public void aplicar(Droga droga){if(vivo){temperatura=Math.max(37,temperatura-droga.reducaoTemperatura());pac=(int)Math.min(100000L,(long)pac+droga.aumentoPac());}}
    public void avancarMinuto(){
        if(!vivo)return;
        minutosQuente=temperatura>41?minutosQuente+1:0;
        minutosPacBaixo=pac<50000?minutosPacBaixo+1:0;
        if(minutosQuente>60||minutosPacBaixo>=30)vivo=false;
    }
}
