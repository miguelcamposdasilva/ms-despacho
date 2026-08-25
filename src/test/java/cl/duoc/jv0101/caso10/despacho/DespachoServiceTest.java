package cl.duoc.jv0101.caso10.despacho;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import cl.duoc.jv0101.caso10.despacho.model.Despacho;
import cl.duoc.jv0101.caso10.despacho.repository.DespachoRepository;
import cl.duoc.jv0101.caso10.despacho.service.DespachoService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DespachoServiceTest {

    @Mock
    private DespachoRepository repository;

    @InjectMocks
    private DespachoService service;

    private Despacho recurso() {
        Despacho r = new Despacho();
        r.setId(1L);
        r.setNombre("Demo");
        r.setRepartidor("valor");
        r.setRuta("valor");
        return r;
    }

    @Test
    void listarRetornaTodos() {
        when(repository.findAll()).thenReturn(List.of(recurso()));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void buscarPorIdExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(recurso()));
        assertThat(service.findById(1L)).isPresent();
    }

    @Test
    void buscarPorIdInexistente() {
        when(repository.findById(9L)).thenReturn(Optional.empty());
        assertThat(service.findById(9L)).isEmpty();
    }

    @Test
    void crearGuarda() {
        when(repository.save(any())).thenReturn(recurso());
        assertThat(service.create(recurso()).getNombre()).isEqualTo("Demo");
    }

@Test
        void actualizarExistente() {
            Despacho datos = recurso();
            datos.setNombre("Actualizado");
            when(repository.findById(1L)).thenReturn(Optional.of(recurso()));
            when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));
            Optional<Despacho> resultado = service.update(1L, datos);
            assertThat(resultado).isPresent();
            assertThat(resultado.get().getNombre()).isEqualTo("Actualizado");
        }

    @Test
    void actualizarInexistente() {
        when(repository.findById(9L)).thenReturn(Optional.empty());
        assertThat(service.update(9L, recurso())).isEmpty();
    }

    @Test
    void eliminarExistente() {
        when(repository.findById(1L)).thenReturn(Optional.of(recurso()));
        assertThat(service.delete(1L)).isTrue();
        verify(repository).delete(any());
    }

    @Test
    void eliminarInexistente() {
        when(repository.findById(9L)).thenReturn(Optional.empty());
        assertThat(service.delete(9L)).isFalse();
    }
}
