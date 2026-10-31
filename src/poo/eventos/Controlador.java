package poo.eventos;
import java.util.*;
public final class Controlador {
    private record Agendado(Evento evento,long ordem){}
    private final PriorityQueue<Agendado> fila=new PriorityQueue<>(Comparator.<Agendado>comparingLong(a->a.evento().instante()).thenComparingLong(Agendado::ordem));
    private long agora=0,ordem=0;
    public long agora(){return agora;}
    public void inserir(Evento evento){Objects.requireNonNull(evento);if(evento.instante()<agora)throw new IllegalArgumentException("Evento no passado");fila.add(new Agendado(evento,ordem++));}
    public void executarAte(long limite){
        if(limite<agora)throw new IllegalArgumentException("Não é possível voltar no tempo");
        while(!fila.isEmpty()&&fila.peek().evento().instante()<=limite){Evento e=fila.remove().evento();agora=e.instante();e.executar(this);}
        agora=limite;
    }
}
