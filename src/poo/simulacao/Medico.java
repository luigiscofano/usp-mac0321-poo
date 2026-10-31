package poo.simulacao;
public final class Medico {
    public boolean consultar(Paciente p,Droga d){
        if(p.vivo()&&p.temperatura()>38&&p.pac()<50000){p.aplicar(d);return true;}
        return false;
    }
}
