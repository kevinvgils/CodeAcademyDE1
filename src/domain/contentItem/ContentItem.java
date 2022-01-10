package domain.contentItem;

public class ContentItem {
    
    public ContentItem() {
    }

    public boolean vallidate(int progress){
        if(progress < 0 && progress > 100){
            return false;
        } else{
            return true;
        }
    }
}
