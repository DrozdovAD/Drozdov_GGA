package Mail_1;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface LetterMapper {

	
		@Select("select fromid,text,toid from Letters")
		List<Letter> loadLetters();

}