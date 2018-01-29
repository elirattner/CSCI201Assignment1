import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.Writer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class UI {
	private Scanner sc;
	public UI()
	{
		List<Student> st = null;
		String fileName = "";
		sc = new Scanner(System.in);
		try 
		{
			FileReader fr;
			BufferedReader br;
			System.out.println("What is the name of the input file?");
			fileName = sc.nextLine();
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			//do gson stuff here
			Gson g = new GsonBuilder().create();
			Obj o = g.fromJson(br, Obj.class);
			//this is your data
			st = o.getStudents(); 
			
			//close stuff here
			try 
			{
				 br.close();
				 fr.close();
			} 
			catch (IOException ioe) 
			{
				System.out.print(ioe.getMessage());
			}
		} 
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
		
		
		program(st, fileName); 	
	}
	
	
	
	
	private void program(List<Student> s, String fileName)
	{
		while(true)
		{
			System.out.println("1) Display Roster");
			System.out.println("2) Add Student");
			System.out.println("3) Remove Student");
			System.out.println("4) Add Grade");
			System.out.println("5) Sort Roster");
			System.out.println("6) Write File");
			System.out.println("7) Exit");
			System.out.println("What would you like to do?");
			int ans = sc.nextInt();
			sc.nextLine();
			if(ans == 1)
			{
				s = display(s);
				
			}
			else if(ans == 2)
			{
				s = addStu(s);
			}
			else if(ans == 3)
			{
				s = removeStu(s);
			}
			else if(ans == 4)
			{
				s = addGrade(s);
			}
			else if(ans == 5)
			{
				s = sort(s); 
			}
			else if(ans == 6)
			{
				try{
					s = write(s, fileName); 
				}
				catch (IOException ioe)
				{
					System.out.println(ioe.getMessage());
				}
			}
			else if(ans == 7)
			{
				exit(s, fileName);
			}
			else
			{
				System.out.println("That is not a valid option");
			}
		}
	}
	
	private List<Student> display(List<Student> s)
	{
		for(int i = 0; i < s.size(); i++)
		{
			System.out.println(s.get(i).toString());
		}
		return s;
	}
	
	private List<Student> addStu(List<Student> s)
	{
		while(true)
		{
			System.out.println("What is the Student's name?");
			String stuName = sc.nextLine();
			if(stuName.indexOf(" ") != stuName.length()-1 || stuName.indexOf(" ") != -1)
			{
				Student st = new Student();
				Name na = new Name();
				na.setFname(stuName.substring(0, stuName.indexOf(" ")));
				na.setLname(stuName.substring(stuName.indexOf(" ")+1));
				st.setName(na);
				s.add(st);
				return s;
			}
			else
			{
				System.out.println("A Student must have a first and last name.");
			}
		}
	}
	
	private List<Student> removeStu(List<Student> s)
	{
		while(true)
		{
			for(int i = 0; i < s.size(); i++)
			{
				System.out.println(i+1+") "+s.get(i).getName().getLname()+", "+s.get(i).getName().getFname());
			}
			System.out.println("Who would you like to remove?");
			int choice = sc.nextInt();
			sc.nextLine();
			if(choice > 0 && choice < s.size()+1)
			{
				s.remove(choice-1);
				return s;
			}
			else
			{
				System.out.print("That is not a valid response.");
			}
		}
	}
	private List<Student> addGrade(List<Student> s)
	{
		while(true)
		{
			for(int i = 0; i < s.size(); i++)
			{
				System.out.println(i+1+") "+s.get(i).getName().getLname()+", "+s.get(i).getName().getFname());
			}
			System.out.println("Who would you like to add a grade to?");
			int choice = sc.nextInt();
			sc.nextLine();
			System.out.println("What grade do you want to enter?");
			int newgrade = sc.nextInt();
			sc.nextLine();
			if(choice > 0 && choice < s.size()+1)
			{
				int ng = s.get(choice-1).getNumGrades();
				double av = s.get(choice-1).getAverage();
				double na = (((ng*av) + newgrade) / (ng+1));
				s.get(choice-1).setAverage(na);
				s.get(choice-1).setNumGrades(ng+1);
				return s;
			}
			else
			{
				System.out.print("That is not a valid response.");
			}
		}
	}
	
	
	private void exit(List<Student> s, String fileName)
	{
		System.out.println("1) Save file.");
		System.out.println("2) Do not save file.");
		int ans = sc.nextInt();
		sc.nextLine();
		if(ans == 1)
		{
			try{
				s = write(s, fileName); 
			}
			catch (IOException ioe)
			{
				System.out.println(ioe.getMessage());
			}
		}
		sc.close();
		System.out.println("Thank you for using my program!");
		System.exit(0);
	}
	
	
	
	private List<Student> sort(List<Student> s)
	{
		System.out.println("1) Alphabetically");
		System.out.println("2) GPA");
		int comp = sc.nextInt();
		sc.nextLine();
		if(comp == 1)
		{
			//sort alphabetically
			for(int i = 0; i < s.size(); i++)
			{
				for(int x = 0; x <s.size()-i-1; x++)
				{
					String xone = s.get(x+1).getName().getLname();
					String xguy = s.get(x).getName().getLname();
					if(xguy.compareTo(xone) > 0)
					{
						Student temp = s.get(x);
						s.set(x, s.get(x+1));
						s.set(x+1, temp);
					}
				}
			}
		}
		else if(comp == 2)
		{
			//sort GPA
			for(int i = 0; i < s.size(); i++)
			{
				for(int x = 0; x <s.size()-i-1; x++)
				{
					if(s.get(x).getAverage() > s.get(x+1).getAverage())
					{
						Student temp = s.get(x);
						s.set(x, s.get(x+1));
						s.set(x+1, temp);
					}
				}
			}
		}
		else
		{
			System.out.println("That is not a valid option");
		}
		return s;
	}
	
	
	
	private List<Student> write(List<Student> s, String fN) throws IOException
	{
		Writer writer = new FileWriter(fN);
        Gson gson = new GsonBuilder().create();
        gson.toJson(s, writer);
        writer.close();
        return s;
	}



public static void main(String[] args){
    UI u = new UI();
}
}