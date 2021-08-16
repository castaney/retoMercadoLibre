package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MutantDnaEnum {

    CCCC("CCCC"),
    TTTT("TTTT"),
    AAAA("AAAA"),
    GGGG("GGGG");
    private String ARN;

    MutantDnaEnum(String ARN) {
        this.ARN = ARN;

    }

    public static boolean getDnaMutant(String codigo){
        List<MutantDnaEnum> stream=Arrays.stream(MutantDnaEnum.values()).filter(x->x.getARN().equals(codigo)).collect(Collectors.toList());

        return stream!=null && !stream.isEmpty();

    }

    /**
     * Metodo que permite obtener el valor del atributo  ARN
     **/
    public String getARN() {
        return ARN;
    }

  
}