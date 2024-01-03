import React, { Component } from "react";
import "../styles/App.css"
import SearchBar from "../components/SearchBar"

class MainNavbar extends Component {
	render() {
		return (
			<div className="mainNavbar">
				<nav className="navbar">
					<ul className="nav justify-content-start">
						&nbsp;
						<a className="navbar-brand"
							href="/"
							style={{ color: "red", fontFamily: "fantasy", fontWeight: 'bold', fontSize: 27 }} > 
						NITFLEX
						</a>

						<li className="nav-item">
							<a className="nav-link" href="/" style={{ color: "white" }}>
								홈
							</a>
						</li>
						<li className="nav-item">
							<a className="nav-link" href="/movie" style={{ color: "white" }}>
								영화
							</a>
						</li>

						<li className="nav-item">
							<a className="nav-link" href="/newContent" style={{ color: "white" }}>
								NEW! 요즘 대세 콘텐츠
							</a>
						</li>

						<li className="nav-item">
							{(sessionStorage.getItem('user') !== null) ?
							<a
								className="nav-link"
								href="/myContent"
								style={{ color: "white" }}
							>
								내가 찜한 콘텐츠
							</a>
							: ''
							} 
						</li>
					</ul>
					<ul className="nav justify-content-end">
						<li>
						{/* <SearchBar handleSubmit={this.handleSubmit} handleChange={this.handleChange} /> */}
						<SearchBar />
						</li>
						<li className="nav-item">
							<div>
								{sessionStorage.getItem("user") == null ? (
								<a className="nav-link" href="/login" style={{ color: "white" }} >
									로그인
								</a>
								) : (
								<a
									className="nav-link"
									href="/logout"
									onClick={this.onLogout}
									style={{ color: "white" }}
								>
									로그아웃
								</a>
								)}
							</div>
						</li>
					</ul>
				</nav>
			</div>
		);
		
	}
}

export default MainNavbar;