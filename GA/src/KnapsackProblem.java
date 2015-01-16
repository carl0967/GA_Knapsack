
/**
 * ナップサック問題
 * @author info
 *
 */
public class KnapsackProblem {
	private int weight[]={2,3,2,9,3,4,5,6,6,3};//各品物の重さ
	private int value[]={1,8,4,10,5,6,8,14,9,2};//各品物の価値
	private int maxWeight=15;//ナップサックに入れられる最大の重さ　15
	private int maxElement=10;//品物の要素数
	public KnapsackProblem(){

	}
	public int getMaxElement(){
		return maxElement;
	}
	/**
	 * 遺伝子xのナップサック問題としての価値を評価する
	 * @param x
	 * @return 遺伝子x価値
	 */
	public int calcMaxWeight(int[] x){
		int ans=0;
		int wei=0;
		for(int i=0;i<maxElement;i++){
			if(x[i]==1){
				ans+=value[i];
				wei+=weight[i];
				if(wei>maxWeight) return 1;//重さが最大値を超えたのなら、価値１とする
			}
		}
		return ans;
	}
}
