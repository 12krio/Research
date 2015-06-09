package kuromoji;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import mem.MemState;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.atilika.kuromoji.Tokenizer.Builder;
import org.atilika.kuromoji.Tokenizer.Mode;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		MemState.getInfo();
		// この２行で解析できる
		Builder builder = Tokenizer.builder();
		builder.userDictionary("userdict.txt");
		Tokenizer tokenizer = builder.mode(Mode.SEARCH).build();
		MemState.getInfo();

		List<Token> tokens = tokenizer.tokenize("事業部長小山田武");
		MemState.getInfo();

		// 結果を出力してみる
		for (Token token : tokens) {
		    System.out.println("==================================================");
		    System.out.println("allFeatures : " + token.getAllFeatures());
		    System.out.println("partOfSpeech : " + token.getPartOfSpeech());
		    System.out.println("position : " + token.getPosition());
		    System.out.println("reading : " + token.getReading());
		    System.out.println("surfaceFrom : " + token.getSurfaceForm());
		    System.out.println("allFeaturesArray : " + Arrays.asList(token.getAllFeaturesArray()));
		    System.out.println("辞書にある言葉? : " + token.isKnown());
		    System.out.println("未知語? : " + token.isUnknown());
		    System.out.println("ユーザ定義? : " + token.isUser());
		}
		MemState.getInfo();

	}

}
