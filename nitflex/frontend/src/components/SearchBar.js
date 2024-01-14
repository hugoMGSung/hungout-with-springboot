import React, { Component } from 'react';
import * as Icon from 'react-bootstrap-icons';

class SearchBar extends Component {
	render() {
        return (
            <div>
                <form className="form-inline" action="">
                    <div className="d-inline-flex">
                        <input
                            className="form-control mr-sm-2"
                            type="text" placeholder="Search" />
                        <button
                            className="btn btn-success"
                            type="submit"
                            // onClick={this.moveSearchMovie}
                            // 여기서 쓰면 버튼을 눌렀을때 
                            // 핸들서브밋과 무브서치무비를 
                            // 같이 실행해서 데이터를 받기전에 
                            // 넘어가는 경우가생김 36~7 번째줄
                            // 데이터를 저장하고 검색한영화들 보여주는 화면으로 넘어감
                        >
                            <Icon.Search/>
                        </button>
                            {/* <SearchMovie movies={this.state.movies} /> */}
                    </div>
                </form>
            </div>
        );
    }
}

export default SearchBar;