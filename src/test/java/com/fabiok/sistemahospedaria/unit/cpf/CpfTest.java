package com.fabiok.sistemahospedaria.unit.cpf;

import com.fabiok.sistemahospedaria.Cpf;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CpfTest {
    @Test
    void deveRetornarCpfValidoFormatado(){
        Cpf cpf = new Cpf("936.288.840-80");
        assertEquals("93628884080", cpf.getValor());
    }
}
