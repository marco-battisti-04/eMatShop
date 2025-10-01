export interface UserResponseRegister{
  userId: string;
  email: string;
  name: string;
  surname: string;
}

export interface UserRequestRegister{
  email: string;
  name: string;
  surname: string;
  password: string;
}

export interface UserResponseLogin{
  token: string;
}

export interface UserRequestLogin{
  email: string;
  password: string;
}
