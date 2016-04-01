/**
 * 
 */
package model;

/**
 * @author piou
 *
 */
public class SetBoxesOrganized extends SetBoxes {

	public SetBoxesOrganized() {
		super();
	}
	
	@Override
	@Deprecated
	/**
	 * We delete the add(Item) method, because now when we had it must be organized
	 */
	public void add(Item element) {
	}
	
	public void add(Item elementToPlace, Item ItemOfReference, String direction){
		
		if(!set.contains(ItemOfReference)){
			System.out.println("# ERROR The item \n" + ItemOfReference + "\n is not in the set of box");
			return;
		}else if(ItemOfReference == null){ //first box of the set
			elementToPlace.setPosition(0, 0, 0);
			return;
		}
		
		
		Item IoF = ItemOfReference; //to simplify the expression
		switch(direction){
		case "up":
			elementToPlace.setPosition(
					IoF.getFrontPos(),
					IoF.getLeftPos(),
					IoF.getUpPos() + IoF.getDepth());					
			break;
		case "left":
			elementToPlace.setPosition(
					IoF.getFrontPos(),
					IoF.getLeftPos()+IoF.getHeight(),
					IoF.getUpPos());	
			break;
		case "front":
			elementToPlace.setPosition(
					IoF.getFrontPos()+IoF.getWidth(),
					IoF.getLeftPos(),
					IoF.getUpPos());	
			break;
		default:
			System.out.println("# ERROR  '"+direction+"' must be equalt to 'up', 'left', or 'front'");
			break;
		#}
	}

}
