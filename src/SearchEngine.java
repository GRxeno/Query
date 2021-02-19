public class SearchEngine {

    public static void main (String[] args){
        Database database = new Database();
        database.createDB("DB2.txt");
        Index index = new Index();
        index.indexDB(database);
        QueryProcessor queryProcessor = new QueryProcessor(index);
       while (true) {
           queryProcessor.run();
       }
    }
}
