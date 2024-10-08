import { useContext } from 'react';
import { Link } from 'react-router-dom';
import '../CSS/NavBar.css';

//enums
import { view } from '../enums/view';

//context
import { AuthContext } from '../context/AuthContext';

//image
import Logo from '../assets/images/CalorieTrackerLogo.webp'

const genericLinks: string[] = ["Foods", "Exercises"]

const navLinksList: { [key in view]: string[] } = {
  [view.GUEST]: ["Pricing", "Contact", ...genericLinks, "Login", "Register"],
  [view.USER]: ["Home", ...genericLinks, "Logout"],
  [view.ADMIN]: ["Home", ...genericLinks, "Logout"]
  //"Users", "Modify Foods", "Modify Exercises",  //Other possibly needed admin links
}

const buildNavLinks = (list: Array<string>): JSX.Element[] => {
  const listItems: JSX.Element[] = [];



  list.forEach((linkName) => {
    listItems.push(
      <li key={linkName}>
        <Link to={"/" + linkName}>{linkName}</Link>
      </li>
    )
  })

  return listItems;
}

const NavigationBar = () => {

  const { role } = useContext(AuthContext);

  const navLinks = buildNavLinks(navLinksList[role]);

  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <Link to="/">
          <img src={Logo} className='logo' />
          <h1>Calorie Tracker</h1>
        </Link>
      </div>
      <ul className="navbar-links">
        {navLinks}
      </ul>
    </nav>
  );
}

export default NavigationBar;
