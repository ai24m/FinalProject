import { Pipe, PipeTransform } from '@angular/core';
import { User } from '../models/user';

@Pipe({
  name: 'userDelete'
})
export class UserDeletePipe implements PipeTransform {

  transform(users: User[], showAll?: boolean): User[] {
    const result: User[] = [];
    if (showAll) {
      return users;
    }
    users.forEach(user => {
      if (!user.disabled) {
        result.push(user);
      }
    })
    return result;
  }

}
