export type UserRoles = "CLIENT" | "PROVIDER";

export type user = {
    id: number;
    name: string;
    lastName: string;
    email:string;
    password:string;
    userRoles: UserRoles;
    phone: string;
    address: string;
}