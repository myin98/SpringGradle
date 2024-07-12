import axios from 'axios'
export default {
	data() {
    	return {
			url1: "/v2/edit",
			url2: "/v2/detail",
			url3: "/v4/findList",
			no: 0,
			check: false,
			map: {}
		}
    },
    created() {
		let paths = location.pathname.split("/");
		if(paths.length == 4) {
			this.no = paths[3];
			axios.post(`${this.url2}/${this.no}`)
				.then(this.success).catch(this.error);
		} else {
			this.backEvent();
		}
	},
	methods: {
		formEvent() {
			this.check = true;
			axios.post(this.url1, null, {params: this.map}).then(this.success).catch(this.error);
		},
		acceptEvent(accept) {
			this.check = true;
			axios.post((accept == undefined) ? `${this.url2}/${this.map.no}` : `${this.url2}/${this.map.no}/${accept}`, null, {params: this.map})
				.then(this.success).catch(this.error);
		},
		success(res) {
			if(res.status == 200) {
				this.map = res.data;
				if(this.check) alert("수정이 성공 하였습니다.");
				this.check = false;
			} else {
				alert("수정이 실패 하였습니다.");
			}
			  
		},
		error(err) { 
			console.log(err) 
		},
		backEvent() {
			location.href = this.url3;
		}
	}
}