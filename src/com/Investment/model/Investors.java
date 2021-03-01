package com.Investment.model;

public class Investors {

	private Integer idInverstors;
	private String name;
	private String surname;
	private Integer money;

	public Integer getIdInverstors() {
		return idInverstors;
	}

	public void setIdInverstors(Integer idInverstors) {
		this.idInverstors = idInverstors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Investors(Integer idInverstors, String name, String surname,
			Integer money) {
		this.idInverstors = idInverstors;
		this.name = name;
		this.surname = surname;
		this.money = money;
	}

	public Investors() {

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Investors [idInverstors=");
		builder.append(idInverstors);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", money=");
		builder.append(money);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idInverstors == null) ? 0 : idInverstors.hashCode());
		result = prime * result + ((money == null) ? 0 : money.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investors other = (Investors) obj;
		if (idInverstors == null) {
			if (other.idInverstors != null)
				return false;
		} else if (!idInverstors.equals(other.idInverstors))
			return false;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
