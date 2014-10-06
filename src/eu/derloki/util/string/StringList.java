package eu.derloki.util.string;

import java.util.ArrayList;

public class StringList extends ArrayList<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8456729364677262461L;

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		String paramStr = (String)o;
		for(String s : this){
			if(paramStr.equalsIgnoreCase(s)) return true;
		}
		return false;
	}
	
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		if (o == null) {
            for (int i = 0; i < size(); i++)
                if (get(i)==null)
                    return i;
        } else {
            for (int i = 0; i < size(); i++)
                if (((String)o).equalsIgnoreCase(get(i)))
                    return i;
        }
        return -1;
	}
	
}
