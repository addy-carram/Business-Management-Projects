package Stu0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/*Credits. Create a set of credit offers from different banks for a client who wants
to take out a loan so they can make an optimal choice. Through a menu,
allow the user to search for a credit offer and calculate what amount they will have
over the desired period. After choosing the credit offer, display the details about the given
offer, the table with the addition they will receive in the account and the available amount. All data will be
stored in a file.*/

interface ContractC{
    int number=1;
    LocalDate signing_date=LocalDate.now();
    String main_manager="Alexandru Popescu";
    abstract void add();
}

class Contract implements ContractC{///interface implementation

    Client client;//composition implementation so a contract includes data from other classes
    Credit credit;
    CreditCard card;

    public Contract(int number, String main_manager, Client client, Credit credit, LocalDate signing_date) {
        this.client = client;
        this.credit = credit;
    }

    public Contract(int number, String main_manager, Client client, CreditCard card, LocalDate signing_date) {
        this.client = client;
        this.card = card;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void add() {//add an element to our list
        St1.contracts.add(new Contract(St1.contracts.size() + 1, main_manager, client, credit, LocalDate.now()));
        System.out.println("Contract has been added");
    }

    public void writeToFile() {// writing to the contracts.txt file
        String filePath = "C:\\Users\\adina\\IdeaProjects\\untitled\\src\\Stu0\\contracts.txt";

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (credit != null) {
                writer.write(this.displayCredit() + "\n\n");
            } else if (card != null) {
                writer.write(this.displayCard() + "\n\n");
            } else {
                writer.write("Invalid contract: without credit or card.\n\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public String displayCredit() {//method to display list elements
        return "Contract ID: " + getNumber() +
                "\nAgent: " + main_manager +
                "\nClient: " + client.last_name + " " + client.first_name + ", Personal ID: " + client.getPersonalId() +
                "\nPhone " + client.phone + " Address " + client.address +
                "\nCredit ID: " + credit.getId() +
                "\nCredit_name " + credit.name + ", Amount: " + credit.amount + " Bank " + credit.bank_name +
                "\nInterest: " + credit.interest_rate + "%" + " Type " + credit.interest_rate_type +
                "\nMonthly payment " + credit.monthly_payment + " Total " + credit.total_amount + " Interest payment " + (credit.total_amount - credit.amount) +
                "\nSigning date: " + signing_date;
    }

    public String displayCard() {
        return "Contract ID: " + getNumber() +
                "\nAgent: " + main_manager +
                "\nClient: " + client.last_name + " " + client.first_name + ", Personal ID: " + client.getPersonalId() +
                "\nPhone " + client.phone + " Address " + client.address +
                "\nCredit-Card ID: " + card.getId() +
                "\nCard_name " + card.card_name + ", Limit: " + card.limit + " Type " + card.type +
                "\nCurrency: " + card.currency + " Funding " + card.funding +
                "\nCard expiration date " + card.expiration_date +
                "\nSigning date: " + signing_date;
    }
}

class Credit{
    private int id;//id is always private
    String name;
    int amount;
    int interest_rate;
    String interest_rate_type;
    int monthly_payment;
    LocalDate term;//data type for date
    int total_amount;
    String bank_name;

    Credit(int id, String name, int amount, int interest_rate,
           String interest_rate_type, int monthly_payment, LocalDate term,
           int total_amount, String bank_name){

        this.id=id;
        this.name=name;
        this.amount=amount;
        this.interest_rate=interest_rate;
        this.interest_rate_type=interest_rate_type;
        this.monthly_payment=monthly_payment;
        this.term=term;
        this.total_amount=total_amount;
        this.bank_name=bank_name;
    }

    public int getId() {
        return id;
    }
}

class CreditCard {
    int id;
    String card_name;
    String type;           // Ex: "Standard", "Gold", "Platinum"
    String currency;        // Ex: "MDL", "RON", "EUR"
    String funding;    // Ex: "Salary", "Bank transfer", "Cash"
    int limit;           // Maximum card limit
    LocalDate expiration_date;

    CreditCard(int id, String card_name, String type,
               String currency, String funding, int limit,
               LocalDate expiration_date) {

        this.id=id;
        this.card_name = card_name;
        this.type = type;
        this.currency = currency;
        this.funding = funding;
        this.limit = limit;
        this.expiration_date = expiration_date;
    }

    public int getId() {
        return id;
    }
}

//inheritance implementation
class Refinancing extends Contract {
    private int desired_financed_amount;
    
    public Refinancing(int number, String manager, Client client, Credit credit,
                       int desired_financed_amount) {
        super(number, manager, client, credit, signing_date);
        this.desired_financed_amount = desired_financed_amount;
    }

    public Refinancing(int number, String manager, Client client, CreditCard card,
                       int desired_financed_amount) {
        super(number, manager, client, card, signing_date);
        this.desired_financed_amount = desired_financed_amount;
    }
    
    @Override
    public String toString() {
        return "Refinancing ID: " + number +
                "\nAgent: " + main_manager +
                "\nClient: " + client.last_name + " " + client.first_name + ", Personal ID: " + client.getPersonalId() +
                "\nCredit: " + credit.name +
                "\nRefinanced amount: " + desired_financed_amount;
    }
}

class Client {
    String last_name;
    String first_name;
    private long personal_id;
    String phone;
    String address;

    Client(long personal_id, String last_name, String first_name, String phone, String address){
        this.last_name=last_name;
        this.first_name=first_name;
        this.personal_id=personal_id;
        this.phone=phone;
        this.address=address;
    }

    public long getPersonalId() {
        return personal_id;
    }
}

class St1 {
    //creating lists within the test class St1
    static ArrayList<Credit> credits=new ArrayList<>();
    static ArrayList<CreditCard> cards=new ArrayList<>();
    static ArrayList<Client> clients=new ArrayList<>();
    static ArrayList<Contract> contracts=new ArrayList<>();
    static ArrayList<Refinancing> refinancings=new ArrayList<>();
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Credit");
            System.out.println("2. Credit-Card-Online");
            System.out.println("3. Contract");
            System.out.println("4. Refinancing");
            System.out.print("Choose option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:{
                    readCreditFileType();
                    System.out.println("Enter desired amount 0-500000:");
                    int desired_amount = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if(desired_amount>0 && desired_amount<500001){
                        System.out.println("Enter maximum interest:");
                        int maximum_interest = scanner.nextInt();
                        if(maximum_interest>0){
                            scanner.nextLine(); // Consume newline
                            int k=0;
                            System.out.println("Enter desired rate type (1 Fixed/ 2 Variable)");
                            String rate_type = scanner.nextLine();

                            // a contract with the exact amount the client wants is not always found,
                            // that's why we use approximately 20% difference
                            if(rate_type.equalsIgnoreCase("Fixed") || rate_type.equalsIgnoreCase("Variable")){
                                double marginPercent = 0.20;
                                double minLimit = desired_amount * (1 - marginPercent);
                                double maxLimit = desired_amount * (1 + marginPercent);

                                for (Credit credit : credits) {
                                    if(credit.amount >= minLimit &&
                                            credit.amount <= maxLimit && maximum_interest<=credit.interest_rate && rate_type.equalsIgnoreCase(credit.interest_rate_type)){
                                        System.out.println("=== Credit ===\n" +
                                                " Id:"+credit.getId()+"\n"+
                                                "Name: " + credit.name + "\n" +
                                                "Amount: " + credit.amount + " RON\n" +
                                                "Interest rate: " + credit.interest_rate + "%\n" +
                                                "Interest rate type: " + credit.interest_rate_type + "\n" +
                                                "Monthly payment: " + credit.monthly_payment + " RON\n" +
                                                "Term: " + credit.term + "\n" +
                                                "Total amount: " + credit.total_amount + " RON\n" +
                                                "Bank: " + credit.bank_name + "\n");
                                    }
                                    else {k++;}
                                }
                                if(k==0) {
                                    System.out.println("It seems nothing suitable was found. Look at the following offers");
                                    displayCreditsType();
                                }
                                System.out.println("Do you want a credit or refinancing, choose one of the options Contract or Refinancing");
                                System.out.println("Otherwise press 0");
                            }
                            else System.out.println("Enter correctly Fixed or Variable interest");
                        }
                        else System.out.println("You entered a negative number");
                    }
                    else System.out.println("You entered a negative number or your amount exceeds the established limit");
                }break;
                
                case 2:{
                    readCardFileType();
                    displayCreditsType();
                    System.out.println("Enter limit:");
                    int desired_amount = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    int k=0;
                    if(desired_amount>0 && desired_amount<200000){
                        System.out.println("Currency type EUR, MDL or RON:");
                        String type = scanner.nextLine();
                        if(type.equalsIgnoreCase("EUR") ||type.equalsIgnoreCase("RON") ||type.equalsIgnoreCase("MDL")){
                            double marginPercent = 0.20;
                            double minLimit = desired_amount * (1 - marginPercent);
                            double maxLimit = desired_amount * (1 + marginPercent);
                            for(CreditCard card:cards){
                                if(card.currency.equalsIgnoreCase(type) && card.limit >= minLimit && card.limit <= maxLimit){
                                    System.out.println("Record #" + card.id +
                                            " | " + card.card_name +
                                            " | " + card.type +
                                            " | " + card.currency +
                                            " | " + card.funding +
                                            " | " + card.limit +
                                            " | " + card.expiration_date);
                                    k++;
                                }
                            }
                            if(k==15) {
                                System.out.println("Unfortunately nothing suitable was found. See all offers.");
                                displayCards();
                            }
                            System.out.println("Do you want a credit or refinancing, choose one of the options Contract or Refinancing");
                            System.out.println("Otherwise press 0");
                        }
                        System.out.println("Enter currency correctly");
                    }
                    else System.out.println("Enter a positive number or fit within the amount of 100000");
                }
                break;
                
                case 3: {
                    readClientsFile();
                    readCreditFileType();
                    System.out.println("Enter your personal ID ex 2567891234567");
                    long id = scanner.nextLong();
                    if (id > 0) {
                        int op;
                        do {
                            System.out.println("Choose a contract type 1 Credit/ 2 Credit-Card, otherwise 0");
                            op = scanner.nextInt();
                            switch (op) {
                                case 1: {
                                    Client foundClient = null;
                                    for (Client c : clients) {
                                        if (c.getPersonalId() == id) {
                                            foundClient = c;
                                            break;
                                        }
                                    }

                                    if (foundClient != null) {
                                        System.out.println("Enter desired credit ID 1-30:");
                                        int credit_id = scanner.nextInt();
                                        scanner.nextLine();

                                        Credit foundCredit = null;
                                        for (Credit cr : credits) {
                                            if (cr.getId() == credit_id) {
                                                foundCredit = cr;
                                            }
                                        }
                                        if (foundCredit != null) {
                                            Contract newContract = new Contract(
                                                    St1.contracts.size() + 1,
                                                    "Alexandru Popescu", foundClient, foundCredit,
                                                    LocalDate.now()
                                            );
                                            newContract.add();
                                            newContract.writeToFile();
                                            St1.contracts.add(newContract);
                                            System.out.println("Contract has been added and saved to file:");
                                            System.out.println(newContract.displayCredit());
                                        }else System.out.println("No such credit found. Try again");
                                        break;
                                    }
                                }break;
                                
                                case 2: {
                                    readClientsFile();
                                    readCardFileType();
                                    Client foundClient = null;
                                    for (Client c : clients) {
                                        if (c.getPersonalId() == id) {
                                            foundClient = c;
                                            break;
                                        }
                                    }

                                    if (foundClient != null) {
                                        System.out.println("Enter desired credit-card ID 1-15:");
                                        int credit_id = scanner.nextInt();
                                        scanner.nextLine();

                                        CreditCard foundCreditC = null;
                                        for (CreditCard cr : cards) {
                                            if (cr.getId() == credit_id) {
                                                foundCreditC = cr;
                                            }
                                        }
                                        if (foundCreditC != null) {
                                            Contract newContract = new Contract(
                                                    St1.contracts.size() + 1,
                                                    "Alexandru Popescu",
                                                    foundClient,
                                                    foundCreditC,
                                                    LocalDate.now()
                                            );

                                            St1.contracts.add(newContract);
                                            newContract.writeToFile();

                                            System.out.println("Contract has been added and saved to file:");
                                            System.out.println(newContract.displayCard());
                                        }else System.out.println("No such credit-card found. Try again");
                                        break;
                                    }
                                }
                                break;
                                
                                case 0: {
                                    System.out.println("Thank you for your attention");
                                }
                                break;
                            }
                        } while (op != 0);
                    } else System.out.println("You entered a negative number");
                }break;
                
                case 4:
                    readClientsFile();
                    System.out.println("Enter your personal ID ex 2567891234567");
                    long id= scanner.nextLong(); 
                    int k=0;
                    if(id>0){
                        scanner.nextLine();
                        Client foundClient = null;
                        Credit foundCredit = null;
                        CreditCard foundCard=null;

                        System.out.println("Your contracts:");
                        for (Contract c : contracts) {
                            if (c.client.getPersonalId() == id) {
                                System.out.println(c);
                                k++;
                                foundClient = c.client;
                                foundCredit = c.credit;
                                foundCard=c.card;
                                if(foundCard!=null){
                                    System.out.println(c.displayCard());
                                }
                                if(foundCredit!=null){
                                    System.out.println(c.displayCredit());
                                }
                            }
                        }
                        if(k!=0) {
                            System.out.println("You have "+k+" contracts");
                            System.out.println("Refinancing accepted. Enter desired amount");
                            int financed_amount=scanner.nextInt();
                            scanner.nextLine();
                            if(foundCredit!=null){
                                Refinancing refinancing = new Refinancing(St1.contracts.size() + 1,
                                        "Alexandru Popescu", foundClient, foundCredit, financed_amount);
                                System.out.println(refinancing);
                            }
                            if(foundCard!=null){
                                Refinancing refinancing = new Refinancing(St1.contracts.size() + 1,
                                        "Alexandru Popescu", foundClient, foundCard, financed_amount);
                                System.out.println(refinancing);
                            }
                        }
                        else System.out.println("You don't have any contract. Do you want one? Press 3");
                        System.out.println("Goodbye!");
                    }else System.out.println("Enter a personal ID as a positive number");
                    break;
                    
                case 0:
                    System.out.println("Thank you for your trust");
                default:
                    System.out.println("Invalid option."); 
                    break;
            }
        } while (option != 0);
    }

    //reading from file in a separate method
    static void readCreditFileType(){
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\adina\\IdeaProjects\\untitled\\src\\Stu0\\credit.txt"))) {
            String line;
            int k=0; //counting number of credits;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s*,\\s*");

                if (parts.length >= 9) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int amount =Integer.parseInt(parts[2]);
                    int interest_rate = Integer.parseInt(parts[3]);
                    String interest_rate_type = parts[4];
                    int monthly_payment = Integer.parseInt(parts[5]);
                    LocalDate term=LocalDate.parse(parts[6]);
                    int total_amount = Integer.parseInt(parts[7]);
                    String bank_name = parts[8];

                    credits.add(new Credit(id, name, amount, interest_rate,
                            interest_rate_type, monthly_payment, term,
                            total_amount, bank_name));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    static void readCardFileType(){
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\adina\\IdeaProjects\\untitled\\src\\Stu0\\credit_tip_card.txt"))) {
            String line;  
            int k=0; //counting number of credits;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");

                if (parts.length >= 6) {
                    int id = Integer.parseInt(parts[0]);
                    String card_name = parts[1];
                    String type = parts[2];
                    String currency=parts[3];
                    String funding = parts[4];
                    int limit=Integer.parseInt(parts[5]);
                    LocalDate expiration_date=LocalDate.parse(parts[6]);

                    cards.add(new CreditCard(id, card_name, type, currency, funding, limit, expiration_date));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    static void readClientsFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\adina\\IdeaProjects\\untitled\\src\\Stu0\\clienti.txt"))) {
            String line;
            int k=0; //counting number of credits;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s*,\\s*");

                if (parts.length >= 5) {
                    long id = Long.parseLong(parts[0]);
                    String last_name = parts[1];
                    String first_name = parts[2];
                    String phone = parts[3];
                    String address=parts[4];
                    clients.add(new Client(id, last_name, first_name, phone, address));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    static void displayCards(){
        for (CreditCard card : cards) {
            System.out.println("Record #" + card.id +
                    " | " + card.card_name +
                    " | " + card.type +
                    " | " + card.currency +
                    " | " + card.funding +
                    " | " + card.limit +
                    " | " + card.expiration_date);
        }
    }
    
    static void displayCreditsType() {
        for (Credit credit : credits) {
            System.out.println("=== Credit ===\n" +
                    " Id:"+credit.getId()+"\n"+
                    "Name: " + credit.name + "\n" +
                    "Amount: " + credit.amount + " RON\n" +
                    "Interest rate: " + credit.interest_rate + "%\n" +
                    "Interest rate type: " + credit.interest_rate_type + "\n" +
                    "Monthly payment: " + credit.monthly_payment + " RON\n" +
                    "Term: " + credit.term + "\n" +
                    "Total amount: " + credit.total_amount + " RON\n" +
                    "Bank: " + credit.bank_name + "\n");
        }
    }
}