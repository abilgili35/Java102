public class MyList <T extends Object> {
    private Object[] list;
    private int index;

    public MyList(){
        list = new Object[10];
        index = 0;
    }

    public MyList(int capacity){
        list = new Object[capacity];
        index = 0;
    }

    public int size(){
        return index;
    }

    public int getCapacity(){
        return list.length;
    }

    public void add(T data){
        if(getCapacity() == size()){
            
            Object[] temp = new Object[list.length];
            for(int i=0; i<temp.length; i++){
                temp[i] = list[i];
            }

            list = new Object[list.length*2];
            for(int i=0; i<temp.length; i++){
                list[i] = temp[i];
            }
        }
        list[index] = data;
        this.index++;
    }

    public T get(int index){
        if((index < 0) || (index > size())){
            return null;
        }
        return (T) list[index];
    }

    public T remove(int index){
        T returnValue;
        Object[] temp = new Object[list.length];

        if((index < 0) || (index > size())){
            return null;
        }

        returnValue = (T) list[index];
        
        for(int i=0; i<temp.length; i++){
            temp[i] = list[i];
        }

        for(int i=0; i<index; i++){
            list[i] = temp[i];
        }

        for(int i=index+1; i<temp.length; i++){
            list[i-1] = temp[i];
        }
        
        this.index--;

        return returnValue;
    }

    public T set(int index, T data){
        
        if((index < 0) || (index > size())){
            return null;
        }

        this.list[index] = data;

        return (T) this.list[index];
    }

    public int indexOf(T data){
        int returnValue = -1;

        for(int i=0; i<this.index; i++){
            if(this.list[i].equals(data)){
                returnValue = i;
                break;
            }
        }

        return returnValue;
    }

    public int lastIndexOf(T data){
        int returnValue = -1;

        for(int i=this.index - 1; i>= 0; i--){
            if(this.list[i].equals(data)){
                returnValue = i;
                break;
            }
        }

        return returnValue;
    }

    public boolean isEmpty(){
        if(size() == 0){
            return true;
        }

        return false;
    }

    public T[] toArray(){
        return (T[]) this.list;
    }

    public void clear(){
        this.index = 0;
    }

    public boolean contains(T data){
        
        if(indexOf(data) != -1){
            return true;
        }

        return false;
    }

    public MyList<T> subList(int start, int finish){
        int minCapacity = (finish - start) + 1;
        int capacity;

        if( (start < 0) || (start > this.size() - 1) || (finish < 0) || (finish > this.size() - 1) || (start > finish) ){
            return new MyList<T>(10);
        }
        
        if(minCapacity <= 10){
            capacity = 10;
        }else{
            capacity = (int) (Math.ceil(minCapacity / 10) * 10);
        }
        
        MyList<T> subList = new MyList<>(capacity);
        
        for(int i=start; i<=finish; i++){
            subList.add(this.get(i));
        }

        return subList;
    } 

    public String toString(){
        String returnValue = "[";
        for(int i=0; i<index; i++){
            returnValue += list[i];
            if(i < (index-1)){
                returnValue += ",";
            }
        }
        returnValue += "]";

        return returnValue;
    }
    
}
