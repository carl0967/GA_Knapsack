import java.util.ArrayList;
/**
 * 交叉方法のインターフェース
 * @author info
 *
 */

public interface Crossover {
	public void clossover(Gene a,Gene b,ArrayList<Gene> genes);
}
