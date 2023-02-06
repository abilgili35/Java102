import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class LeagueFixture {

    
    static class OrderByRoundComparator implements Comparator<Round>{

        @Override
        public int compare(LeagueFixture.Round o1, LeagueFixture.Round o2) {
            if(o1.getId() > o2.getId()){
                return 1;
            }else if(o1.getId() < o2.getId()){
                return -1;
            }else{
                return 0;
            }
        }
        
    }
    
    
    static class Game {
        String homeTeam;
        String awayTeam;

        public Game(String hTeam, String aTeam){
            setHomeTeam(hTeam);
            setAwayTeam(aTeam);
        }

        public String getHomeTeam() {
            return homeTeam;
        }

        public void setHomeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
        }

        public String getAwayTeam() {
            return awayTeam;
        }

        public void setAwayTeam(String awayTeam) {
            this.awayTeam = awayTeam;
        }

        public void print(){
            System.out.println(getHomeTeam() + " vs " + getAwayTeam() + " " + hashCode());
        }
        
        
        public boolean equals (Object obj) {
            if (this == obj)
                return true;
    
            if (this == null)
                return false;
    
            if (this.getClass() != obj.getClass())
                return false;
    
            Game g = (Game) obj;
            
            if(this.hashCode() == g.hashCode()){
                return true;
            }else{
                return false;
            }
            
        }
        

        @Override
        public int hashCode() {
            return getHomeTeam().hashCode() * getAwayTeam().hashCode();
        }
        
        
    }

    static class Round{
        private int id;
        private int maxGames;
        HashSet<Game> games;

        public Round(int id, int maxGames){
            setId(id);
            setMaxGames(maxGames);
            games = new HashSet<>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMaxGames() {
            return this.maxGames;
        }

        public void setMaxGames(int maxGames) {
            this.maxGames = maxGames;
        }

        public boolean addGame(Game game){
            return games.add(game);
        }

        public HashSet<Game> getRoundGames(){
            return this.games;
        }

        public void clear(){
            this.games.clear();
        }
    }

    

    static Round createRound(int roundId, ArrayList<String> teams, HashSet<Game> fixture){
       
        ArrayList<String> remainingTeams = new ArrayList<String>();
        Round round = new Round(roundId, teams.size()/2);
        Random r = new Random();
        Game game = null;
        boolean gameCreated = false;

        

        while(true){
            
            gameCreated = false;

            remainingTeams.clear();
            remainingTeams.addAll(teams);

            if(round.getMaxGames() == round.getRoundGames().size()){
                break;
            }

            for(Game g : round.getRoundGames()){
                remainingTeams.remove(g.getHomeTeam());
                remainingTeams.remove(g.getAwayTeam());
            }

            int randomNum = Math.abs(r.nextInt(0, remainingTeams.size()));
            String homeTeam = remainingTeams.get(randomNum);
            remainingTeams.remove(homeTeam);
            
            while(!gameCreated){
                randomNum = Math.abs(r.nextInt(0, remainingTeams.size()));
                String awayTeam = remainingTeams.get(randomNum);
                remainingTeams.remove(awayTeam);

                game = new Game(homeTeam, awayTeam);

                if(fixture.contains(game)){
                    round.addGame(game);
                    fixture.remove(game);
                    gameCreated = true;
                 }else{
                    if(remainingTeams.size() == 0){
                        remainingTeams.clear();
                        remainingTeams.addAll(teams);
                        for(Game g : round.getRoundGames()){
                            fixture.add(g);
                        }
                        round.clear();
                        break;
                    }
                    
                    
                }
            }
             
            
            
        }
        return round;
        

    }

    static void printFixture(ArrayList<Round> fixture){
        
        for(Round round : fixture){
            System.out.println();
            System.out.println("Round " + round.getId());
            System.out.println();
            for(Game game : round.getRoundGames()){
                System.out.println(game.getHomeTeam() + " vs " + game.getAwayTeam());
            }
        }

        for(Round round : fixture){
            System.out.println();
            System.out.println("Round " + (round.getId() + fixture.size()));
            System.out.println();
            for(Game game : round.getRoundGames()){
                System.out.println(game.getAwayTeam() + " vs " + game.getHomeTeam());
            }
        }

    }

    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> teams = new ArrayList<>();
        ArrayList<Round> allFixture = new ArrayList<>();
        HashSet<Round> fixture = new HashSet<>();
        HashSet<Game> allGames = new HashSet<>();
        
        String input = "";

        do{
            System.out.println("Lutfen takim ismini giriniz.Sonlandirmak icin e tusuna basin");
            input = s.next();
            if(input.equalsIgnoreCase("e")){
                break;
            }
            teams.add(input);
        }while(true);

        if((teams.size() % 2) == 1){
            teams.add("Bay");
        }

        System.out.println("\nTakimlar");
        for(String team : teams){
            System.out.println("- " + team);
        }

        System.out.println();

        for(String hTeam : teams){
            for(String aTeam : teams){
                if(hTeam.equals(aTeam)){
                    continue;
                }
                allGames.add(new Game(hTeam, aTeam));
            }
        }


        
        int numberOfRounds = (teams.size() - 1);
        
        for(int i=1; i<=numberOfRounds; i++){
            fixture.add(createRound(i, teams, allGames));
        }


        for(Round r : fixture){
            allFixture.add(r);
        }

        Collections.sort(allFixture, new OrderByRoundComparator());
        printFixture(allFixture);
        


    }
    
}
