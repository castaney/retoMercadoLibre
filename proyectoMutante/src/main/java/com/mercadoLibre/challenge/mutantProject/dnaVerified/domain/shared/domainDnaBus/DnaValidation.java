package com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.shared.domainDnaBus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.constants.MessagesModel;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception.DnaStructureException;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception.ModelError;
import com.mercadoLibre.challenge.mutantProject.dnaVerified.domain.model.dna.exception.ValidadorResult;
/**
 * Componente que valida la informacion del ADN chequeando si es o
 * no mutante
 * @author Uberney Castaneda Garzon <castaney@gmail.com>
 *
 */
@Component
public class DnaValidation implements DomainDnaBus {

	

	/**
	 * Metodo que verifica en la lista de strings si el adn
	 * corresponde a un mutante revisando secuencia de AAAA, CCCC, DDDD, TTTT 
	 * tanto vertical, diagonal y horizontalmente
	 */
	@Override
	public boolean isMutant(List<String> listDna) throws DnaStructureException {

		ValidadorResult<Boolean> validaInfo = verifyStructure(listDna);
		if (validaInfo.isError()) {
			throw new DnaStructureException(validaInfo.getMensaje());
		}
		Long countMutatn = 0L;
		if (validaInfo.ok()) {

			List<String> strings = getDiagonalInvertStrings(listDna);
			Map<Integer, String> verticalStrings = getVerticalStrings(listDna);
			strings.addAll(getMapList(verticalStrings));
			listDna.addAll(strings);

			for (String stringDnaI : listDna) {
				// Se verifica horizontalmente
				countMutatn = countMutatn + getCountDna(stringDnaI);
			}
		}

		return (countMutatn != null && countMutatn > 1 ? Boolean.TRUE : Boolean.FALSE);

	}
	/**
	 * permite obtener la diagonal y la diagonal invertida de la lista de strings
	 * @param dnaList, lista que viene por parametro
	 * @return lista de strings con las dos diagonales
	 */
	private List<String> getDiagonalInvertStrings(List<String> dnaList) {
		int contDiagonal = 0;
		int contInvertedDiagonal = dnaList.get(0).length();
		List<String> listResult = new ArrayList<String>();
		String stringResult = "";
		String stringResultInv = "";

		for (String string : dnaList) {
			if (contDiagonal >= string.length() || contInvertedDiagonal <= 0) {
				break;
			}
			//se recorre uno a uno sumando siempre un valor para que tome  la primera diagonal
			stringResult += string.substring(contDiagonal, contDiagonal + 1);
			contDiagonal++;
			//se recorre uno a uno sumando siempre un valor para que tome  la segunda diagonal
			stringResultInv += string.substring(contInvertedDiagonal - 1, contInvertedDiagonal);
			contInvertedDiagonal--;
		}
		listResult.add(stringResult);
		listResult.add(stringResultInv);

		return listResult;
	}

	/**
	 * Este metodo permite obtner una lista con la informacion recuperada del mapa
	 * 
	 * @param verticalStrings, mapa con los strings verticales
	 * @return lista de strings
	 */
	private Collection<String> getMapList(Map<Integer, String> verticalStrings) {

		List<String> cadenas = verticalStrings.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
		return cadenas;
	}

	/**
	 * Se recorre la informacion de la lista de strings
	 * y se llama a metodo por cada iteracion para rearmar
	 * la secuencia pero de forma vertical
	 * @param stringList
	 * @return
	 */
	private Map<Integer, String> getVerticalStrings(List<String> stringList) {
		Map<Integer, String> strings = new HashMap<Integer, String>();
		for (String string : stringList) {
			getVerticalListXString(string, strings);
		}
		return strings;
	}

	/**
	 * Este metodo permite pasar por referencia al mapa cada una de las letras
	 * verticales dentro del arreglo de string
	 * De esta forma se organiza segun la posicion la cadena que se
	 * debera construir verticalmente
	 * 
	 */
	private void getVerticalListXString(String string, Map<Integer, String> strings) {

		for (int i = 0; i <= string.length() - 1; i++) {
			if (!strings.containsKey(i)) {
				strings.put(i, String.valueOf(string.charAt(i)));
			} else {
				String back = strings.get(i);
				strings.remove(i);
				strings.put(i, back + String.valueOf(string.charAt(i)));
			}
		}

	}

	/**
	 * Metodo que permite verificar la estructura de listDna, si las columnas y las
	 * filas no corresponden saca error, o si hay letras que no corresponden a las
	 * asociadas como letra valida
	 * 
	 * @param listDna Dna de mutante o humano
	 * @return ValidadorResult<Boolean> que determina si puede o no continuar
	 *         evaluando la cadena
	 */
	@SuppressWarnings("unchecked")
	private ValidadorResult<Boolean> verifyStructure(List<String> listDna) {
		List<String> listaCadenaErradas = listDna.stream().filter(x -> x.matches(MessagesModel.PATTERN_LETTERS_FAILED) || x.length() < 4).collect(Collectors.toList());
		if (listaCadenaErradas!=null && !listaCadenaErradas.isEmpty()) {
		
			return ValidadorResult.error(new ModelError(MessagesModel.STRING_FAIL_IN_STRUCTURE,MessagesModel.MODEL_01,listaCadenaErradas.get(0)));
		}

		Boolean colFileNoEqual=listDna.stream().anyMatch(x-> x.length()!=listDna.size());
		if(colFileNoEqual) {
			return ValidadorResult.error(new ModelError(MessagesModel.COL_FILE_ERROR, MessagesModel.MODEL_02, null));
		}
		return ValidadorResult.ok(Boolean.TRUE);
	}

	/**
	 * Metodo que permite contar las coincidencia de las letras especificadas dentro
	 * de la cadena stringDNA
	 * 
	 * @param stringDna
	 * @return
	 */
	private int getCountDna(String stringDna) {
		int veces = stringDna.length() - 3;
		int posInicial = 0;
		//Secuencia de 4 letras
		int posFinal = 4;
		int contar = 0;
		for (int i = 0; i <= (veces - 1); i++) {
			//Se selecciona desde la posicion inicial a la posicion final (+4)
			String cadena = stringDna.substring(posInicial, posFinal);
			posInicial = posInicial + 1;
			posFinal = posInicial + 4;
			//Si es una cadena mutante 
			if (MutantDnaEnum.getDnaMutant(cadena)) {
				contar++;
				//si faltan menos de cuatro caracteres por verificar
				if ((stringDna.length() - posFinal) <3) {

					break;
				}
			}
		}

		return contar;
	}

}
