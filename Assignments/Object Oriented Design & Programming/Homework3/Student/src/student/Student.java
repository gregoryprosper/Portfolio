package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Class for representing Students
 */
public class Student {

	/**
	 * Constructor which takes name and date object
	 */
	Student(String name, Date whenEnrolled) {
		this.name = name;
		this.enrollment = (Date) whenEnrolled.clone();
	}

	/**
	 * Returns student name
	 * 
	 * @return student name as string
	 */
	public String getName() {
		return this.name;
	}

	
	/**
	 * Returns student name
	 * 
	 * @return student enrollment date as Date object
	 */
	public Date getEnrollment() {
		return (Date) this.enrollment.clone();
	}

	/**
	 * Returns Comparator to sort by name
	 * 
	 * @return Comparator to sort by name
	 */
	public static Comparator<Student> getCompByName() {
		return new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
				return student1.getName().compareTo(student2.getName());
			}
		};
	}

	/**
	 * Returns Comparator to sort by enrollment date
	 * 
	 * @return Comparator to sort by enrollment date
	 */
	public static Comparator<Student> getCompByDate() {
		return new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
				return student1.getEnrollment().compareTo(
						student2.getEnrollment());
			}
		};
	}

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<>();
		Date date = new Date();

		date.setDate(31);
		date.setMonth(1);
		date.setYear(1992);
		Student s1 = new Student("Prosper, Gregory", date);
		studentList.add(s1);

		date.setDate(30);
		date.setMonth(7);
		date.setYear(1992);
		Student s2 = new Student("Jean, Mideline", date);
		studentList.add(s2);

		date.setDate(18);
		date.setMonth(8);
		date.setYear(2010);
		Student s3 = new Student("Prosper, Kenny", date);
		studentList.add(s3);

		date.setDate(11);
		date.setMonth(6);
		date.setYear(2001);
		Student s4 = new Student("Miller, Jerry", date);
		studentList.add(s4);

		System.out.println("No Sort:");
		for (Student student : studentList) {
			System.out.println(student.getName());
		}

		System.out.println("\nSort By Name:");
		Collections.sort(studentList, Student.getCompByName());

		for (Student student : studentList) {
			System.out.println(student.getName());
		}

		System.out.println("\nSort By Date:");
		Collections.sort(studentList, Student.getCompByDate());

		for (Student student : studentList) {
			System.out.println(student.getName());
		}

	}

	private String name;
	private Date enrollment;
}
