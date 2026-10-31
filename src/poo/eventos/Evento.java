package poo.eventos;
/** Evento de domínio: o controlador não aceita Runnable. */
public abstract class Evento {
    private final long instante;
    protected Evento(long instante){if(instante<0)throw new IllegalArgumentException("Instante negativo");this.instante=instante;}
    public final long instante(){return instante;}
    public abstract void executar(Controlador controlador);
}
