package mgr;

import java.util.Scanner;

public interface Factory4<T extends Manageable4> {
	public T create(Scanner scan);

}
