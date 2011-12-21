<?php
class StoredProcedure
		extends DBCall {

	/**
	 * @var String
	 */

	private $name;

	public function __construct($name) {
		$this->name = $name;
		$this->setManager(new Manager());
	}

	public function execute() {
		if (!$this->get_validator()
				->validate($data))
			return Error::get_error(Error::CODE_INVALID_PARAMETERS, $data);
		$result = $this->get_manager()
				->execute_procedure($this->name, $data);
		return $this->get_formater()
				->format($result);
	}

}
